/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { TblVertragDetailComponent } from 'app/entities/tbl-vertrag/tbl-vertrag-detail.component';
import { TblVertrag } from 'app/shared/model/tbl-vertrag.model';

describe('Component Tests', () => {
    describe('TblVertrag Management Detail Component', () => {
        let comp: TblVertragDetailComponent;
        let fixture: ComponentFixture<TblVertragDetailComponent>;
        const route = ({ data: of({ tblVertrag: new TblVertrag(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [TblVertragDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TblVertragDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TblVertragDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tblVertrag).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});

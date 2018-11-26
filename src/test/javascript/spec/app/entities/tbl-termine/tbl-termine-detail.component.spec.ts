/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { TblTermineDetailComponent } from 'app/entities/tbl-termine/tbl-termine-detail.component';
import { TblTermine } from 'app/shared/model/tbl-termine.model';

describe('Component Tests', () => {
    describe('TblTermine Management Detail Component', () => {
        let comp: TblTermineDetailComponent;
        let fixture: ComponentFixture<TblTermineDetailComponent>;
        const route = ({ data: of({ tblTermine: new TblTermine(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [TblTermineDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TblTermineDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TblTermineDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tblTermine).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});

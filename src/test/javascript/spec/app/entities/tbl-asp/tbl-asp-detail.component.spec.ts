/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { TblAspDetailComponent } from 'app/entities/tbl-asp/tbl-asp-detail.component';
import { TblAsp } from 'app/shared/model/tbl-asp.model';

describe('Component Tests', () => {
    describe('TblAsp Management Detail Component', () => {
        let comp: TblAspDetailComponent;
        let fixture: ComponentFixture<TblAspDetailComponent>;
        const route = ({ data: of({ tblAsp: new TblAsp(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [TblAspDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TblAspDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TblAspDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tblAsp).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
